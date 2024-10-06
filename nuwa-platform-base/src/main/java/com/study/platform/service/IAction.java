 package com.study.platform.service;

 @FunctionalInterface
 public interface IAction<T> {
     void run(T param);
 }

