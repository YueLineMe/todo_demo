package com.nf147.todo.mapper;

import com.nf147.todo.entity.Todo;

import java.util.List;

public interface TodoMapper {
    /**
     *
     * 获得全部
     *
     * @return
     */
    List<Todo> getTodos();

    int addTodo(Todo todo);

    int deleteTodo(Todo todo);

    /**
     *  修改任务为完成
     *  @param todo 反正没用的东西
     */
    int makeDone(Todo todo);

    /**
     *  修改未完成
     *  @param todo 你懂的
     * */
    int makeCancel(Todo todo);
}
