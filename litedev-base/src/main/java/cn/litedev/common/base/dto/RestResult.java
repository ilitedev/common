/*
     Litedev Projects
     Copyright (C) 2020 litedev.cn

     This program is free software: you can redistribute it and/or modify
     it under the terms of the GNU General Public License as published by
     the Free Software Foundation, either version 3 of the License, or
     (at your option) any later version.

     This program is distributed in the hope that it will be useful,
     but WITHOUT ANY WARRANTY; without even the implied warranty of
     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
     GNU General Public License for more details.

     You should have received a copy of the GNU General Public License
     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package cn.litedev.common.base.dto;

import lombok.Getter;

/**
 * @author fanlei
 */
@Getter
public class RestResult<T> {

    private boolean success;

    private String code;

    private String message;

    private T data;

    private Pageinfo pageinfo;

    private RestResult(boolean success, String code, String message, T data, Pageinfo pageinfo) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
        this.pageinfo = pageinfo;
    }

    public static <T> RestResult<T> success() {
        return new RestResult<>(true, null, null, null, null);
    }

    public static <T> RestResult<T> fail() {
        return new RestResult<>(false, null, null, null, null);
    }

    public RestResult<T> code(String code) {
        this.code = code;
        return this;
    }

    public RestResult<T> message(String message) {
        this.message = message;
        return this;
    }

    public RestResult<T> pageinfo(Pageinfo pageinfo) {
        this.pageinfo = pageinfo;
        return this;
    }

    public RestResult<T> data(T data) {
        this.data = data;
        return this;
    }
}
