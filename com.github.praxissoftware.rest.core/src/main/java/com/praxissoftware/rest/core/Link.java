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

import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.base.Preconditions;

/**
 * Representation object for links. This object is based on the Atom representation for links. We use Links to convey hypermedia in our resource representations.
 * 
 * @see <a href="http://tools.ietf.org/html/rfc4287#appendix-B">RFC 4287</a>
 * @see <a href="http://roy.gbiv.com/untangled/2008/rest-apis-must-be-hypertext-driven">Hypermedia and HATEOAS</a>
 * 
 * @author Devon Tackett
 * @author Jason Rose
 */
@XmlRootElement
public class Link extends AbstractMapEntity {

  /**
   * Default constructor.
   */
  public Link() {
    this(null, null);
  }

  public Link(final Object... pairs) {
    Preconditions.checkNotNull(pairs);
    Preconditions.checkArgument(pairs.length % 2 == 0);
    for( int i = 0; i < pairs.length; i += 2 ) {
      put((String) pairs[i], pairs[i + 1]);
    }
  }

  /**
   * Constructs with the given target and relationship.
   * @param href The URI of the link target.
   * @param rel The relationship type.
   */
  public Link(final URI href, final String rel) {
    this(href, rel, null);
  }

  /**
   * Constructs with the given target, relationship, and content type.
   * @param href The URI of the link target.
   * @param rel The relationship type.
   * @param type The content type of the target.
   */
  public Link(final URI href, final String rel, final String type) {
    this(href, rel, type, null);
  }

  /**
   * Constructs with the given target, relationship, content type, and title.
   * @param href The URI of the link target.
   * @param rel The relationship type.
   * @param type The content type of the target.
   * @param title A displayable title for the link.
   */
  public Link(final URI href, final String rel, final String type, final String title) {
    this(href, rel, type, title, null);
  }

  /**
   * Constructs with the given target, relationship, content type, title, and language.
   * @param href The URI of the link target.
   * @param rel The relationship type.
   * @param type The content type of the target.
   * @param title A displayable title for that link.
   * @param hrefLang The language of the target.
   */
  public Link(final URI href, final String rel, final String type, final String title, final String hrefLang) {
    this(href, rel, type, title, hrefLang, null);
  }

  /**
   * Constructs with the given target, relationship, content type, title, language, and response size.
   * @param href The URI of the link target.
   * @param rel The relationship type.
   * @param type The content type of the target.
   * @param title A displayable title for that link.
   * @param hrefLang The language of the target.
   * @param length The content length of the link target, in bytes.
   */
  public Link(final URI href, final String rel, final String type, final String title, final String hrefLang, final String length) {
    this("href", href, "rel", rel, "type", type, "title", title, "hrefLang", hrefLang, "length", length);
  }

  /**
   * Returns the link's target URI.
   * @return The link's target URI.
   */
  public URI getHref() {
    return getAndCoerce("href");
  }

  /**
   * Returns the link's target language.
   * @return The link's target language.
   */
  public String getHrefLang() {
    return getAndCoerce("hrefLang");
  }

  /**
   * Returns the link's target's content length, in bytes.
   * @return The link's target's content length, in bytes.
   */
  public String getLength() {
    return getAndCoerce("length");
  }

  /**
   * Returns the link's relationship.
   * @return The link's relationship.
   */
  public String getRel() {
    return getAndCoerce("rel");
  }

  /**
   * Returns the link's human-readable title.
   * @return The link's human-readable title.
   */
  public String getTitle() {
    return getAndCoerce("title");
  }

  /**
   * Returns the link's target's content type.
   * @return The link's target's content type.
   */
  public String getType() {
    return getAndCoerce("type");
  }

  /**
   * Sets the link's target URI.
   * @param href The link's target URI.
   */
  public void setHref(final URI href) {
    put("href", href);
  }

  /**
   * Sets the link's target language.
   * @param hrefLang The link's target language.
   */
  public void setHrefLang(final String hrefLang) {
    put("hrefLang", hrefLang);
  }

  /**
   * Sets the link's target's content length, in bytes.
   * @param length The link's target's content length, in bytes.
   */
  public void setLength(final String length) {
    put("length", length);
  }

  /**
   * Sets the link's relationship.
   * @param rel The link's relationship.
   */
  public void setRel(final String rel) {
    put("rel", rel);
  }

  /**
   * Sets the link's human-readable title.
   * @param title The link's human-readable title.
   */
  public void setTitle(final String title) {
    put("title", title);
  }

  /**
   * Sets the link's target's content type.
   * @param type The link's target's content type.
   */
  public void setType(final String type) {
    put("type", type);
  }
}
