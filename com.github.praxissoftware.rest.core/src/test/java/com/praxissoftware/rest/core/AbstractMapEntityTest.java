/**
 * Copyright 2012 Jason Rose <jasoncrose@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.praxissoftware.rest.core;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import junit.framework.Assert;

import org.junit.Test;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class AbstractMapEntityTest {

  @Test
  public void testMapConstructorIsDefensive() {
    final Map<String, Object> map = Maps.newHashMap();
    map.put("test", Lists.newArrayList("one"));
    final A a = new A(map);
    Assert.assertEquals(map.get("test"), a.get("test"));
    map.clear();
    Assert.assertNotNull(a.get("test"));
  }

  @Test
  public void testSortedMapConstructorPreservesSorting() {
    final A a = new A(Maps.<String, Object> newTreeMap());
    a.put("a", "one");
    a.put("c", "two");
    a.put("b", "three");
    final Iterator<String> keys = a.keySet().iterator();
    Assert.assertEquals("a", keys.next());
    Assert.assertEquals("b", keys.next());
    Assert.assertEquals("c", keys.next());

    final Iterator<? extends Object> vals = a.values().iterator();
    Assert.assertEquals("one", vals.next());
    Assert.assertEquals("three", vals.next());
    Assert.assertEquals("two", vals.next());
  }

  @Test
  public void testSortedMapConstructorPreservesComparator() {
    final A a = new A(new TreeMap<String, Object>(new Comparator<String>() {
      @Override
      public int compare(final String o1, final String o2) {
        return o2.compareTo(o1);
      }
    }));
    a.put("a", "one");
    a.put("c", "two");
    a.put("b", "three");
    final Iterator<String> keys = a.keySet().iterator();
    Assert.assertEquals("c", keys.next());
    Assert.assertEquals("b", keys.next());
    Assert.assertEquals("a", keys.next());

    final Iterator<? extends Object> vals = a.values().iterator();
    Assert.assertEquals("two", vals.next());
    Assert.assertEquals("three", vals.next());
    Assert.assertEquals("one", vals.next());
  }

  @Test
  public void testImmutableSortedMap() {
    final A a = new A(ImmutableSortedMap.<String, Object> of("a", "one", "c", "two", "b", "three"));
    final Iterator<String> keys = a.keySet().iterator();
    Assert.assertEquals("a", keys.next());
    Assert.assertEquals("b", keys.next());
    Assert.assertEquals("c", keys.next());

    final Iterator<? extends Object> vals = a.values().iterator();
    Assert.assertEquals("one", vals.next());
    Assert.assertEquals("three", vals.next());
    Assert.assertEquals("two", vals.next());

    try {
      a.clear();
      Assert.fail();
    } catch( final UnsupportedOperationException uoe ) {
      // pass
    }
  }

  @Test
  public void testClear() {
    final A a = new A();
    a.put("one", "two");
    Assert.assertEquals("two", a.get("one"));
    a.clear();
    Assert.assertNull(a.get("one"));
  }

  @Test
  public void testContainsKey() {
    final A a = new A();
    a.put("one", "two");
    Assert.assertTrue(a.containsKey("one"));
    Assert.assertFalse(a.containsKey("two"));
  }

  @Test
  public void testContainsValue() {
    final A a = new A();
    a.put("one", "two");
    Assert.assertFalse(a.containsValue("one"));
    Assert.assertTrue(a.containsValue("two"));
  }

  @Test
  public void testEntrySet() {
    final A a = new A();
    a.put("one", "two");
    final Set<Map.Entry<String, Object>> entries = a.entrySet();
    Assert.assertEquals(1, entries.size());
    final Map.Entry<String, Object> entry = entries.iterator().next();
    Assert.assertEquals("one", entry.getKey());
    Assert.assertEquals("two", entry.getValue());
  }

  @Test
  public void testEquals() {
    final A a = new A();
    a.put("one", "two");
    final Map<String, Object> map = Maps.newHashMap();
    map.put("one", "two");
    Assert.assertEquals(a, map);
    final Map<String, ? extends Object> immutable = ImmutableMap.of("one", "two");
    Assert.assertEquals(a, immutable);
  }

  @Test
  public void testHashCode() {
    final A a = new A();
    a.put("one", "two");
    final Map<String, Object> map = Maps.newHashMap();
    map.put("one", "two");
    Assert.assertEquals(a.hashCode(), map.hashCode());
    final Map<String, ? extends Object> immutable = ImmutableMap.of("one", "two");
    Assert.assertEquals(a.hashCode(), immutable.hashCode());
  }

  @Test
  public void testIsEmpty() {
    final A a = new A();
    Assert.assertTrue(a.isEmpty());
    a.put("one", "two");
    Assert.assertFalse(a.isEmpty());
    a.clear();
    Assert.assertTrue(a.isEmpty());
  }

  @Test
  public void testPut() {
    final A a = new A();
    Assert.assertFalse(a.containsKey("one"));
    a.put("one", "two");
    Assert.assertTrue(a.containsKey("one"));
  }

  @Test
  public void testPutRemovesNulls() {
    final A a = new A();
    a.put("one", "two");
    Assert.assertTrue(a.containsKey("one"));
    a.put("one", null);
    Assert.assertFalse(a.containsKey("one"));
  }

  @Test
  public void testPutAll() {
    final A a = new A();
    final Map<String, ? extends Object> map = ImmutableMap.of("one", "two", "three", "four");
    a.putAll(map);
    Assert.assertEquals(a, map);
  }

  @Test
  public void testRemove() {
    final A a = new A();
    a.put("one", "two");
    Assert.assertTrue(a.containsKey("one"));
    a.remove("one");
    Assert.assertFalse(a.containsKey("one"));
  }

  @Test
  public void testSize() {
    final A a = new A();
    Assert.assertEquals(0, a.size());
    a.put("one", "two");
    Assert.assertEquals(1, a.size());
  }

  @Test
  public void testToString() {
    final A a = new A();
    final Map<String, Object> map = Maps.newHashMap();
    Assert.assertEquals(map.toString(), a.toString());
  }

  @Test
  public void testValues() {
    final A a = new A();
    a.put("one", "two");
    Assert.assertEquals("two", a.values().iterator().next());
  }

  private static final class A extends AbstractMapEntity {
    public A() {
      super();
    }

    public A(final Map<String, Object> map) {
      super(map);
    }
  }
}
