package com.javachen.servlet.normal;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class EchoServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            Path path = File.createTempFile("echo", "tmp").toPath();
            Files.copy(request.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            Files.copy(path, response.getOutputStream());
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}