package pt.tecnico.uilib.forms;

import java.util.function.Function;

/**
 * Request a value obtained by parsing the input, within a Form.
 *
 * <p>The numeric fields differ only in the parser they use, so they share this
 * one class: a parser that throws {@link NumberFormatException} simply reports
 * the input as unacceptable, and the driver asks again.
 *
 * @param <Type> the type produced by the parser.
 */
final class FieldParsed<Type> extends Field<Type> {

  /** Turns the input into a value, or throws NumberFormatException. */
  private final Function<String, Type> _parser;

  /**
   * @param prompt the description used in the request
   * @param parser the conversion to apply to the input
   */
  FieldParsed(String prompt, Function<String, Type> parser) {
    super(prompt);
    _parser = parser;
  }

  /** @see pt.tecnico.uilib.forms.Field#parse(java.lang.String) */
  @Override
  public boolean parse(String in) {
    try {
      set(_parser.apply(in));
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

}
