module com.example.restaurant.kitchenservice {

    exports com.example.restaurant.kitchenservice.application.command;
    exports com.example.restaurant.kitchenservice.application.api;

    requires com.example.restaurant.orderservice;
    requires com.example.restaurant.common.event;

    requires jakarta.annotation;
    requires jakarta.cdi;
    requires jakarta.transaction;
    requires jakarta.persistence;

    requires kotlin.stdlib;

    requires spring.beans;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.data.commons;
    requires spring.data.jpa;
    requires spring.web;
    requires spring.webmvc;

    requires org.slf4j;

    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.kotlin;
}