package br.com.seguros.backend.dao;

import java.util.ArrayList;

public interface CrudInterface<T, U> {

    public void salvar(T a);

    public void salvar(T a, U b);

    public ArrayList<T> buscar();

    public T buscarPorId(Integer id);

    public T editar(T a, Integer id);

    public void deletar(Integer id);
}
