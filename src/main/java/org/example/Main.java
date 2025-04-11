package org.example;

import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.http.defaultContentType = "text/plain";
        }).start(7070);

        app.updateConfig(config -> {
            config.staticFiles.add(staticFileConfig -> {
                staticFileConfig.hostedPath = "/";
                staticFileConfig.directory = "public";
                staticFileConfig.location = io.javalin.http.staticfiles.Location.CLASSPATH;
            });
        });


        app.post("/solve", ctx -> {
            String input = ctx.body();
            String result = CubeSolver.solve(input);
            ctx.result(result);
        });

    }
}
