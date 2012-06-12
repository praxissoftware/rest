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
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.collect.ImmutableMap;

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
public class Link extends AbstractImmutableMapEntity {

  /**
   * The builder follows the Builder recommendation of Effective Java #2.
   * @author Jason Rose
   * 
   */
  public static class Builder {
    private URI uri;
    private String rel;
    private String type;
    private String title;
    private String hrefLang;
    private String length;

    /**
     * Returns a newly-created instance of the Link.
     * @return A newly-created instance of the Link.
     */
    public Link build() {
      return new Link(convertToMap());
    }

    /**
     * Sets the target URI of the Link.
     * @param uri The target URI of the Link.
     * @return The builder, for method chaining.
     */
    public Builder href(final URI uri) {
      this.uri = uri;
      return this;
    }

    /**
     * Sets the target language of the Link.
     * @param hrefLang The target language of the Link.
     * @return The builder, for method chaining.
     */
    public Builder hrefLang(final String hrefLang) {
      this.hrefLang = hrefLang;
      return this;
    }

    /**
     * Sets the Link's target's response size, in bytes.
     * @param length The target's response size, in bytes.
     * @return The builder, for method chaining.
     */
    public Builder length(final String length) {
      this.length = length;
      return this;
    }

    /**
     * Sets the relationship of the Link to the target resource.
     * @param rel The relationship of the Link to the target resource.
     * @return The builder, for method chaining.
     */
    public Builder rel(final String rel) {
      this.rel = rel;
      return this;
    }

    /**
     * Sets a human-readable title of the target resource.
     * @param title A human-readable title of the target resource.
     * @return The builder, for method chaining.
     */
    public Builder title(final String title) {
      this.title = title;
      return this;
    }

    /**
     * Sets the primary content type of the target's response.
     * @param type The primary content type of the target's response.
     * @return The builder, for method chaining.
     */
    public Builder type(final String type) {
      this.type = type;
      return this;
    }

    private Map<String, Object> convertToMap() {
      final ImmutableMap.Builder<String, Object> mapBuilder = ImmutableMap.builder();
      testAndSet(mapBuilder, "href", uri);
      testAndSet(mapBuilder, "rel", rel);
      testAndSet(mapBuilder, "type", type);
      testAndSet(mapBuilder, "title", title);
      testAndSet(mapBuilder, "hrefLang", hrefLang);
      testAndSet(mapBuilder, "length", length);
      return mapBuilder.build();
    }

    private void testAndSet(ImmutableMap.Builder<String, Object> builder, String key, Object value) {
      if( builder != null && key != null && value != null ) {
        builder.put(key, value);
      }
    }
  }

  private Link(final Map<String, Object> params) {
    super(params);
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
}
