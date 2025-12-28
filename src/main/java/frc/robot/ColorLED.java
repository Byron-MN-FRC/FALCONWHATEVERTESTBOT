package frc.robot;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.AddressableLEDBufferView;
import edu.wpi.first.wpilibj.util.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages multiple LED strips on one PWM port using buffer views.
 */
public class ColorLED {
    private AddressableLEDBuffer m_ledBuffer;
    public AddressableLED m_led;
    private List<AddressableLEDBufferView> ledBuffers;

    /**
     * Creates LED strips from lengths array.
     * @param port PWM port
     * @param lengths array of LED counts per strip
     */
    public ColorLED(int port, int[] lengths) {
        int totalLength = 0;
        
        for (int len : lengths) totalLength += len;
        
        m_led = new AddressableLED(port);
        m_ledBuffer = new AddressableLEDBuffer(totalLength);
        //144 lights on big strand
        //75 seems to be max amount before something explodes
        //8 lights on small strand
        m_led.setLength(m_ledBuffer.getLength());

        m_led.setData(m_ledBuffer);

        m_led.start();

        ledBuffers = new ArrayList<>();
        int start = 0;
        for (int len : lengths) {
            int end = start + len - 1;
            ledBuffers.add(m_ledBuffer.createView(start, end));
            start += len;
        }
    }

    /**
     * Sets all LED strips to the same color.
     * @param red red value 0-255
     * @param green green value 0-255
     * @param blue blue value 0-255
     */
    public void setLED(int red, int green, int blue) {
        // TODO test whether inputs greater than 255 or less than 0 need to be clamped

        for (var i = 0; i < m_ledBuffer.getLength(); i++) {
            // Set the value
            m_ledBuffer.setRGB(i, red, green, blue);
        }

        m_led.setData(m_ledBuffer);

    }

    /**
     * Sets a specific LED strip to a color.
     * @param stripIndex index of the strip (0-based)
     * @param red red value 0-255
     * @param green green value 0-255
     * @param blue blue value 0-255
     */
    public void setLED(int stripIndex, int red, int green, int blue) {
        // TODO test whether inputs greater than 255 or less than 0 need to be clamped

        AddressableLEDBufferView view = ledBuffers.get(stripIndex);
        for (int i = 0; i < view.getLength(); i++) {
            // Set the value
            view.setRGB(i, red, green, blue);
        }

        m_led.setData(m_ledBuffer);

    }
}
