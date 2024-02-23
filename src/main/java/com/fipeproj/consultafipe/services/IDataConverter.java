package com.fipeproj.consultafipe.services;

import java.util.List;

public interface IDataConverter {
    <T> T recieveData(String json, Class<T> classe);
    <T> List<T> recieveList(String json, Class<T> classe);
}
