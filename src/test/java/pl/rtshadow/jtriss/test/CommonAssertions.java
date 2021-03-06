/*
   Copyright 2012 Przemysław Pastuszka

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
 
package pl.rtshadow.jtriss.test;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Iterator;

public class CommonAssertions {
  public static <T> void assertTheSameCollection(Iterator<? extends T> first, Iterator<? extends T> second) {
    while (first.hasNext() && second.hasNext()) {
      assertThat(first.next()).isEqualTo(second.next());
    }
    assertThat(first.hasNext()).isEqualTo(second.hasNext());
  }
}
