package dh.backend.dao;

import java.util.List;

public interface IDao <T>{
    T registrar (T t);
    T buscarPorId(Integer id);
    void guardar(T t);
    List<T> buscarTodos();
}
