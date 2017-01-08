/*
 * json-memory-nosql
 * =================
 * 
 * CORSFilter - Thierry COUNILH <thierry.counilh@aticdnet.com>
 * 
 * © Copyright 2016 @tIcdnet, Thierry COUNILH. Tous droits réservés.
 * Reproduction interdite sans l'autorisation écrite de l'auteur.
 */
package fr.mmtc.json.memory;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

/**
 * Cross-Origin Resource Sharing (CORS) Filter
 *
 * @author Thierry COUNILH <thierry.counilh@aticdnet.com>
 */
@Provider
public class CORSFilter implements ContainerResponseFilter {
    /**
     * Add header CORS information for each JAX-RS request
     * 
     * @param paramContainerRequestContext Input container request
     * @param paramContainerResponseContext Output container request
     * @throws IOException 
     */
    @Override
    public void filter(ContainerRequestContext paramContainerRequestContext,
                       ContainerResponseContext paramContainerResponseContext)
            throws IOException {
        paramContainerResponseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
        paramContainerResponseContext.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        paramContainerResponseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
        paramContainerResponseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        paramContainerResponseContext.getHeaders().add("Access-Control-Max-Age", "1209600");
    }
}
