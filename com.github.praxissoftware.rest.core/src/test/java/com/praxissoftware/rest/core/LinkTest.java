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

import java.net.URI;
import java.net.URISyntaxException;

import junit.framework.Assert;

import org.junit.Test;

import com.google.common.collect.ImmutableMap;

public class LinkTest {

  @Test
  public void testBuilder() throws URISyntaxException {
    final Link link = new Link.Builder().href(new URI("/")).rel("foo").type("text/foo").title("Foo").hrefLang("en-US").length("1024").build();
    Assert.assertEquals("/", link.getHref().toString());
    Assert.assertEquals("foo", link.getRel());
    Assert.assertEquals("text/foo", link.getType());
    Assert.assertEquals("Foo", link.getTitle());
    Assert.assertEquals("en-US", link.getHrefLang());
    Assert.assertEquals("1024", link.getLength());
  }

  @Test
  public void testHref() throws URISyntaxException {
    final Link link = new Link.Builder().href(new URI("/")).build();
    Assert.assertEquals("/", link.getHref().toString());
  }

  @Test
  public void testHrefLang() {
    final Link link = new Link.Builder().hrefLang("en-US").build();
    Assert.assertEquals("en-US", link.getHrefLang());
  }

  @Test
  public void testLength() {
    final Link link = new Link.Builder().length("1024").build();
    Assert.assertEquals("1024", link.getLength());
  }

  @Test
  public void testRel() {
    final Link link = new Link.Builder().rel("foo").build();
    Assert.assertEquals("foo", link.getRel());
  }

  @Test
  public void testTitle() {
    final Link link = new Link.Builder().title("Foo").build();
    Assert.assertEquals("Foo", link.getTitle());
  }

  @Test
  public void testType() {
    final Link link = new Link.Builder().type("text/foo").build();
    Assert.assertEquals("text/foo", link.getType());
  }
  
  @Test
  public void testCreateSeedMap() {
    final Link link = new Link.Builder(ImmutableMap.of("test", "one")).rel("two").build();
    Assert.assertEquals("one", link.get("test"));
    Assert.assertEquals("two", link.getRel());
  }
  
  @Test
  public void testImmutability() throws URISyntaxException {
    final Link link = new Link.Builder().build();
    trySetting(link, "href", new URI("/"));
    trySetting(link, "rel", "test");
    trySetting(link, "title", "test");
    trySetting(link, "type", "test");
    trySetting(link, "length", "test");
    trySetting(link, "hrefLang", "test");
  }

  private void trySetting(Link link, String key, Object value) {
    try {
      link.put(key, value);
      Assert.fail(key);
    } catch (UnsupportedOperationException uoe) {
      // pass
    }
  }
}
