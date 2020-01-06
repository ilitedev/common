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
package cn.litedev.common.base.dict;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据字典
 *
 * @author fanlei
 */
public class Dictionary {

    private Map<String, List<DictionaryItem>> all = new HashMap<>();

    /**
     * 新增字典项
     *
     * @param group 字典分组
     * @param item  字典项
     */
    public void add(String group, DictionaryItem item) {
        List<DictionaryItem> cur = all.computeIfAbsent(group, k -> new ArrayList<>());
        cur.add(item);
    }

    public Map<String, List<DictionaryItem>> getAll() {
        return all;
    }

    public List<DictionaryItem> get(String group) {
        return all.get(group);
    }

}