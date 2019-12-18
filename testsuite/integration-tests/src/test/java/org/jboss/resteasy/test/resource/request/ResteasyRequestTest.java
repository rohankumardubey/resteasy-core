package org.jboss.resteasy.test.resource.request;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.resteasy.spi.HttpResponseCodes;
import org.jboss.resteasy.test.resource.request.resource.RequestResource;
import org.jboss.resteasy.utils.PortProviderUtil;
import org.jboss.resteasy.utils.TestUtil;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @tpSubChapter Resource
 * @tpChapter Integration tests
 * @tpTestCaseDetails Tests for ResteasyRequest
 * @tpSince RESTEasy 4.3.2
 */
@RunWith(Arquillian.class)
@RunAsClient
public class ResteasyRequestTest {

   static Client client;
   static WebTarget requestWebTarget;

   @BeforeClass
   public static void before() throws Exception {
      client = ClientBuilder.newClient();
      requestWebTarget = client.target(generateURL("/request"));
   }

   @AfterClass
   public static void close() {
      client.close();
   }

   @Deployment
   public static Archive<?> deploy() {
      WebArchive war = TestUtil.prepareArchive(ResteasyRequestTest.class.getSimpleName());
      return TestUtil.finishContainerPrepare(war, null, RequestResource.class);
   }

   private static String generateURL(String path) {
      return PortProviderUtil.generateURL(path, ResteasyRequestTest.class.getSimpleName());
   }

   /**
    * @tpTestDetails Checks ResteasyRequest
    * @tpSince RESTEasy 4.3.2
    */
   @Test
   public void testRequest() {
      try {
         Response response = requestWebTarget.request().get();
         Assert.assertEquals(HttpResponseCodes.SC_OK, response.getStatus());
         Assert.assertEquals("127.0.0.1/127.0.0.1", response.readEntity(String.class));
         response.close();
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }
   }