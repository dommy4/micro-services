package com.dommy;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class TrialService extends AbstractVerticle {

    @Override
    public void start() {
        Router r= Router.router(this.vertx); //new instance of router

        r.get("/").handler(this::home); //handler
        r.get("/contact").handler(this::contact);
        r.get("/about").handler(this::about);
        r.post("/pull").handler(this::pull);

        this.vertx.createHttpServer() //server to listen port 
        .requestHandler(r)
        .listen(8080);
    }
    public void home(final RoutingContext rc){
        rc.response().sendFile("dommy.html");
    }
    public void about(final RoutingContext rc){
        rc.response().sendFile("about.html");
    }
    public void contact(final RoutingContext rc){
        rc.response().sendFile("contact.html");
    }
    public void pull(final RoutingContext rc){
        rc.request().handler(b->{
            JsonObject getdata=b.toJsonObject();
            rc.response().end(getdata.encodePrettily());
        });
    }
}


  


