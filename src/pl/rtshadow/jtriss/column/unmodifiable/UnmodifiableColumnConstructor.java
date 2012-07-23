package pl.rtshadow.jtriss.column.unmodifiable;

import static java.util.Collections.sort;
import static java.util.Collections.unmodifiableList;

import java.util.ArrayList;
import java.util.List;

import pl.rtshadow.jtriss.column.ColumnConstructor;
import pl.rtshadow.jtriss.column.SortedColumn;
import pl.rtshadow.jtriss.column.element.ColumnElement;
import pl.rtshadow.jtriss.column.element.ModifiableColumnElement;

public class UnmodifiableColumnConstructor<T extends Comparable<? super T>> implements ColumnConstructor<T> {
  private List<ModifiableColumnElement<T>> elements = new ArrayList<ModifiableColumnElement<T>>();
  private boolean hasBeenGenerated = false;

  @Override
  public void add(ModifiableColumnElement<T> element) {
    elements.add(element);
  }

  @Override
  public SortedColumn<T> generate(int id) {
    assureFirstGeneration();

    sort(elements);
    setElementsPositionsAndColumnId(id);

    return new UnmodifiableSortedColumn<T>(
        unmodifiableList(new ArrayList<ColumnElement<T>>(elements)), id);
  }

  private void setElementsPositionsAndColumnId(int columnId) {
    int i = 0;
    for (ModifiableColumnElement<T> element : elements) {
      element.setPosition(i++);
      element.setColumnId(columnId);
    }
  }

  private void assureFirstGeneration() {
    if (hasBeenGenerated) {
      throw new IllegalStateException();
    }
    hasBeenGenerated = true;
  }
}