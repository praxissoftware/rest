package com.praxissoftware.rest.core;

import java.net.URI;
import java.net.URISyntaxException;

import junit.framework.Assert;

import org.junit.Test;

public class LinkTest {

  @Test
  public void testHref() throws URISyntaxException {
    final Link link = new Link.Builder().href(new URI("/")).build();
    Assert.assertEquals("/", link.getHref().toString());
  }
  
  @Test
  public void testRel() {
    final Link link = new Link.Builder().rel("foo").build();
    Assert.assertEquals("foo", link.getRel());
  }
  
  @Test
  public void testType() {
    final Link link = new Link.Builder().type("text/foo").build();
    Assert.assertEquals("text/foo", link.getType());
  }
  
  @Test
  public void testTitle() {
    final Link link = new Link.Builder().title("Foo").build();
    Assert.assertEquals("Foo", link.getTitle());
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
  public void testBuilder() throws URISyntaxException {
    final Link link = new Link.Builder().href(new URI("/")).rel("foo").type("text/foo").title("Foo").hrefLang("en-US").length("1024").build();
    Assert.assertEquals("/", link.getHref().toString());
    Assert.assertEquals("foo", link.getRel());
    Assert.assertEquals("text/foo", link.getType());
    Assert.assertEquals("Foo", link.getTitle());
    Assert.assertEquals("en-US", link.getHrefLang());
    Assert.assertEquals("1024", link.getLength());
  }
}
