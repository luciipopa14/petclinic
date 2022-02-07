package com.endava.petclinic.filters;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.filter.log.LogDetail;
import io.restassured.internal.print.RequestPrinter;
import io.restassured.internal.print.ResponsePrinter;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

public class LogFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger(LogFilter.class);

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext context) {

        OutputStream outputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outputStream);

        RequestPrinter.print(requestSpec, requestSpec.getMethod(), requestSpec.getURI(), LogDetail.ALL, requestSpec.getConfig().getLogConfig().blacklistedHeaders(),
                stream, true);

        Response response = context.next(requestSpec, responseSpec);

        try {
            outputStream.write("\n" .getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        ResponsePrinter.print(response, response.body(), stream, LogDetail.ALL, true, requestSpec.getConfig().getLogConfig().blacklistedHeaders());

        LOGGER.info(outputStream);

        return response;
    }
}
