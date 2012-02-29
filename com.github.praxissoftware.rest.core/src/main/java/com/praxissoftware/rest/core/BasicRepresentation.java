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

import java.util.List;

import com.google.common.collect.Lists;

/**
 * This class is just a simple Representation, for uses where other properties aren't needed.
 * @author Jason Rose
 */
public class BasicRepresentation extends AbstractMapEntity implements Representation {

  public BasicRepresentation() {
    put("links", Lists.newArrayList());
  }

  @Override
  public List<Link> getLinks() {
    return getAndCoerce("links");
  }

  @Override
  public void setLinks(final List<Link> links) {
    put("links", links);
  }

}
