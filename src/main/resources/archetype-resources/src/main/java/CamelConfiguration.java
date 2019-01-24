/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ${package};

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//import org.springframework.context.annotation.Import;


@Component
//@Import(ApiAutoConfiguration.class)
public class CamelConfiguration extends RouteBuilder {

  private static final Logger log = LoggerFactory.getLogger(CamelConfiguration.class);
  
  @Autowired
  private ApiProperties apiProperties;
  
  @Override
  public void configure() throws Exception {

    camelContext.setStreamCaching(true);


    restConfiguration()
            .bindingMode(RestBindingMode.json)
            //Customize in/out Jackson objectmapper, see JsonDataFormat. Two different instances): json.in.*, json.out.*
            .dataFormatProperty("json.in.moduleClassNames", "com.fasterxml.jackson.datatype.jsr310.JavaTimeModule")
            .dataFormatProperty("json.out.include", "NON_NULL")
            .dataFormatProperty("json.out.disableFeatures", "WRITE_DATES_AS_TIMESTAMPS")
            .dataFormatProperty("json.out.moduleClassNames", "com.fasterxml.jackson.datatype.jsr310.JavaTimeModule")
            .contextPath(apiProperties.getBasePath)
            .apiContextPath("/api-docs")
            .apiProperty("api.title", "__API_NAME__")
            .apiProperty("api.version", "__API_VERSION__");

//    rest(apiPath)
//            .description("TroubleTicket API")
//            .consumes("application/json")
//            .produces("application/json")
//
//            .post("/troubleTicket")
//
//            .description("Create TroubleTicket")
//            .type(TroubleTicket.class)
//            .param().name("body").type(RestParamType.body).description("The ticket to update").endParam()
//            .responseMessage().code(202).endResponseMessage()
//            .route().routeId("troubleTicketCreate")
//            .to("bean:troubleTicketService?method=troubleTicketCreate");
  }
}
