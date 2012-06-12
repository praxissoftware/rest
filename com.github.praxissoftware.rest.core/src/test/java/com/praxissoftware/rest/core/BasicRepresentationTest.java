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
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

public class BasicRepresentationTest {

  @Test
  public void testDefaultState() throws URISyntaxException {
    final BasicRepresentation rep = new BasicRepresentation();
    Assert.assertNotNull(rep.getLinks());
    final List<Link> links = Lists.newArrayList();
    links.add(new Link.Builder().href(new URI("/")).build());
    rep.setLinks(links);
    Assert.assertEquals(links, rep.getLinks());
  }
}
