package pt.tecnico.uilib.text;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * CompositePrintStream represents a print stream that encloses more than one
 * print stream. Each message sent to this print stream is also sent to its
 * nested print streams.
 *
 * <p>The duplication happens at the byte level rather than by overriding every
 * {@code print}/{@code println} overload: whatever {@link PrintStream} produces
 * is handed to each nested stream. Anything {@code PrintStream} formats for
 * itself — {@code printf}, {@code format}, {@code append} — is therefore
 * duplicated as well, without an override of its own.
 */
class CompositePrintStream extends PrintStream {

  /** Writes everything it receives to each of the nested streams. */
  private static final class Tee extends OutputStream {

    private final PrintStream[] _streams;

    Tee(PrintStream... streams) {
      _streams = streams;
    }

    @Override
    public void write(int b) {
      for (PrintStream stream : _streams)
        stream.write(b);
    }

    @Override
    public void write(byte[] buffer, int offset, int length) {
      for (PrintStream stream : _streams)
        stream.write(buffer, offset, length);
    }

    @Override
    public void flush() {
      for (PrintStream stream : _streams)
        stream.flush();
    }

    /** Closes the nested streams, but never the process's own output. */
    @Override
    public void close() {
      for (PrintStream stream : _streams)
        if (stream != System.out)
          stream.close();
    }

  }

  /** The nested streams, kept for {@link #checkError()}. */
  private final PrintStream[] _streams;

  /**
   * Creates a new composite print stream.
   *
   * @param printStreams the streams to write to.
   */
  CompositePrintStream(PrintStream... printStreams) {
    super(new Tee(printStreams), true);
    _streams = printStreams;
  }

  /** @see java.io.PrintStream#checkError() */
  @Override
  public boolean checkError() {
    boolean error = super.checkError();
    for (PrintStream stream : _streams)
      error = error || stream.checkError();
    return error;
  }

}
