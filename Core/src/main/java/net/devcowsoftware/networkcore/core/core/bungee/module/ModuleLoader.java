package net.devcowsoftware.networkcore.core.core.bungee.module;

import lombok.Getter;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import org.yaml.snakeyaml.Yaml;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@Getter
public class ModuleLoader {

    @Getter
    private static ModuleLoader coreInstance;

    private final Yaml yaml = new Yaml();
    private final String path;
    private final Map<String, Module> toLoad = new HashMap<>();
    private final List<Module> loadedModules = new ArrayList<>();

    /**
     *
     * Create constructor to load modules by path
     *
     * @param path Path for the modules
     */

    public ModuleLoader( String path ) {
        long l = System.currentTimeMillis();
        coreInstance = this;
        this.path = path;
        new File( path ).mkdirs();
        loadModulesFromFolder();
        while ( !toLoad.isEmpty() ) {
            List<Module> toRemove = new ArrayList<>();
            for ( Module module : toLoad.values() ) {
                for ( String s : module.getDependencies() ) {
                    if ( toLoad.containsKey( s ) ) continue;
                }
                module.onEnable();
                loadedModules.add( module );
                toRemove.add( module );
            }
            for ( Module module : toRemove ) {
                toLoad.remove( module.getName() );
            }
        }
        System.out.println( "loaded " + loadedModules.size() + " modules in " + ( System.currentTimeMillis() - l ) + "ms" );
    }

    /**
     * Load all modules form constructor's given folder
     */

    private void loadModulesFromFolder() {
        for ( File file : Objects.requireNonNull( new File( path ).listFiles() ) ) {
            if ( file.getName().endsWith( ".jar" ) ) {
                try {
                    ZipFile javaFile = new ZipFile( file );
                    ZipEntry entry = javaFile.getEntry( "Bungee-Module.yml" );
                    Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load( new InputStreamReader( javaFile.getInputStream( entry ) ) );
                    String main = config.getString( "main" );
                    Module module = loadIntoRuntime( file, main );
                    module.setName( config.getString( "name" ) );
                    module.setAuthor( config.getString( "author" ) );
                    module.setVersion( config.getDouble( "version" ) );
                    module.setDependencies( config.getStringList( "dependencies" ).toArray( new String[ 0 ] ) );
                    module.setFile( file.getParentFile() );
                    module.setMain( main );
                    module.onLoad();
                    toLoad.put( module.getName(), module );
                    javaFile.close();
                } catch ( IOException | IllegalAccessException | ClassNotFoundException | InstantiationException | NoSuchMethodException | InvocationTargetException e ) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *
     * Load module into runtime of the core
     *
     * @param file the modules path (file)
     * @param mainClass the URI of the main class
     * @return Module
     * @throws NoSuchMethodException
     * @throws MalformedURLException
     * @throws ClassNotFoundException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */

    private Module loadIntoRuntime( File file, String mainClass ) throws NoSuchMethodException, MalformedURLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException {

        ClassLoader loader = URLClassLoader.newInstance(
                new URL[] { file.toURL() },
                getClass().getClassLoader()
        );
        Class<?> clazz = Class.forName(mainClass, true, loader);
        Constructor<?> constructor = clazz.getConstructor();
        return (Module) constructor.newInstance();
    }

}
