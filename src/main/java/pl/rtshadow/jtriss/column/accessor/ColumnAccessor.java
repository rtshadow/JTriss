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
 
package pl.rtshadow.jtriss.column.accessor;

import pl.rtshadow.jtriss.column.element.ColumnElement;
import pl.rtshadow.jtriss.common.ValueRange;

public interface ColumnAccessor<T extends Comparable<? super T>>
    extends Iterable<ColumnElement<T>> {

  void prepareMainColumnForReconstruction();

  void finishReconstruction();

  ReconstructedObject<T> reconstruct(ColumnElement<T> firstElement);

  ColumnAccessor<T> subColumn(ValueRange<T> range);

  int getSize();

  int getId();
}
