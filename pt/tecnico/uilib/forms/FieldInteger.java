package pt.tecnico.uilib.forms;

/**
 * Request an integer within a Form.
 *
 * @deprecated superseded by {@link FieldParsed}, which covers every field whose
 *             input is obtained by parsing: {@code new FieldParsed<>(prompt,
 *             Integer::parseInt)} does the same. Kept for now; {@link Form} no
 *             longer creates it.
 */
@Deprecated
final class FieldInteger extends Field<Integer> {

  /** @param prompt the description used in the request */
  FieldInteger(String prompt) {
    super(prompt);
  }

  /** @see pt.tecnico.uilib.forms.Field#parse(java.lang.String) */
  @Override
  public boolean parse(String in) {
    try {
      set(Integer.parseInt(in));
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

}
