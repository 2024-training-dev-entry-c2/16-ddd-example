package com.zombicide.shared.application;

public interface IQueryUseCase<R> {
  R execute();
}