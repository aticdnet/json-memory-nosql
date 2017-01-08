/*
 * json-memory-nosql
 * =================
 * 
 * StartupManager - Thierry COUNILH <thierry.counilh@aticdnet.com>
 * 
 * © Copyright 2016 @tIcdnet, Thierry COUNILH. Tous droits réservés.
 * Reproduction interdite sans l'autorisation écrite de l'auteur.
 */
package fr.mmtc.json.memory;

import fr.mmtc.json.memory.manager.NoSQLManager;
import fr.mmtc.json.memory.nosql.NoSQLDocument;
import fr.mmtc.json.memory.nosql.web.NoSQLResources;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.jaxrs.JAXRSArchive;
import org.wildfly.swarm.logging.LoggingFraction;
import org.wildfly.swarm.swagger.SwaggerArchive;

/**
 * Startup of this microservice
 *
 * @author Thierry COUNILH <thierry.counilh@aticdnet.com>
 */
public final class StartupManager {
    
    private StartupManager() {
        
    }

    /**
     * Startup main function of this microservice
     * @param args args of the main function
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        final Swarm swarm = new Swarm();
        SwaggerArchive archive = ShrinkWrap.create(SwaggerArchive.class, "swagger-app.war");
        archive.setResourcePackages(NoSQLResources.class.getPackage().getName());
        archive.setTitle("JSON Memory API");
        archive.setContextRoot("/");
        JAXRSArchive deployment = archive.as(JAXRSArchive.class);
        deployment.addPackage(NoSQLManager.class.getPackage());
        deployment.addPackage(NoSQLDocument.class.getPackage());
        deployment.addPackage(NoSQLResources.class.getPackage());
        deployment.addAllDependencies();
        swarm.fraction(LoggingFraction.createDefaultLoggingFraction());
        swarm.start();
        swarm.deploy(deployment);
    }
}
