package pt.tecnico.uilib.forms;

/**
 * Request a real number in a Form.
 *
 * @deprecated superseded by {@link FieldParsed}, which covers every field whose
 *             input is obtained by parsing: {@code new FieldParsed<>(prompt,
 *             Double::parseDouble)} does the same. Kept for now; {@link Form} no
 *             longer creates it.
 */
@Deprecated
final class FieldReal extends Field<Double> {

  /** @param prompt the description used in the request */
  FieldReal(String prompt) {
    super(prompt);
  }

  /** @see pt.tecnico.uilib.forms.Field#parse(java.lang.String) */
  @Override
  public boolean parse(String in) {
    try {
      set(Double.parseDouble(in));
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

}
