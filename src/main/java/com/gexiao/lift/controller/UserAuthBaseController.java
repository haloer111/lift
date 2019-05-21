package com.gexiao.lift.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gexiao.lift.common.Result;
import com.gexiao.lift.entity.core.UserAuthBaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: gexiao
 * @Date: 2019/4/9 14:59
 * @Description: 基类controller
 */
public abstract class UserAuthBaseController<S extends IService<E>, E extends UserAuthBaseEntity> {

    @Autowired
    protected S service;

    /**
     * 根据实体获取列表
     *
     * @param e 查询实体对象
     * @return
     */
    @RequestMapping(value = "list",method = RequestMethod.POST)
    public Result list(@RequestBody E e) {
        List<E> list = service.list(new LambdaQueryWrapper<E>()
                .setEntity(e));
        return Result.ok(list);
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public Result getById(@PathVariable String id) {
        E obj = service.getById(new LambdaQueryWrapper<E>()
                .eq(UserAuthBaseEntity::getId, id));
        return Result.ok(obj);
    }

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public Result delete(@PathVariable String id) {
        boolean b = service.removeById(new LambdaQueryWrapper<E>()
                .eq(UserAuthBaseEntity::getId, id));
        return Result.ok(b);
    }

    /**
     * 新增
     *
     * @param e
     * @return
     */
    @PostMapping("")
    public Result add(@RequestBody E e) {
        boolean b = service.save(e);
        return Result.ok(b);
    }

    /**
     * 更新
     *
     * @param e
     * @return
     */
    @PutMapping("{id}")
    public Result update(@PathVariable String id, @RequestBody E e) {
        e.setId(id);
        boolean b = service.updateById(e);
        return Result.ok(b);
    }
}
