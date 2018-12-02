package com.nf147.todo.controller;

import com.nf147.todo.entity.Todo;
import com.nf147.todo.mapper.TodoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/todo")
@CrossOrigin
public class TodoController {

    @Autowired(required = false)
    private TodoMapper todoMapper;

    @RequestMapping(method = RequestMethod.GET)
    public List<Todo> home() {
        return todoMapper.getTodos();
    }

    @RequestMapping(method = RequestMethod.POST)
    public List<Todo> add(@RequestBody Todo todo) {
        int result = todoMapper.addTodo(todo) == 0 ? 200 : 500;
        return todoMapper.getTodos();
    }

    @RequestMapping("/del/{id}")
    public List<Todo> del(@PathVariable("id") int id) {
        Todo todo = new Todo();
        todo.setId(id);
        int result = todoMapper.deleteTodo(todo) == 0 ? 200 : 500;
        return todoMapper.getTodos();
    }

    @RequestMapping("/done/{id}")
    public List<Todo> Done(@PathVariable("id") int id) {
        Todo todo = new Todo();
        todo.setId(id);
        int result = todoMapper.makeDone(todo) == 0 ? 200 : 500;
        return todoMapper.getTodos();
    }
    @RequestMapping("/cancel/{id}")
    public List<Todo> Cancel(@PathVariable("id") int id) {
        Todo todo = new Todo();
        todo.setId(id);
        int result = todoMapper.makeCancel(todo) == 0 ? 200 : 500;
        return todoMapper.getTodos();
    }

}
