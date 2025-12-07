package frc.robot;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.util.Color;

public class ColorLED {
    private AddressableLEDBuffer m_ledBuffer;
    public AddressableLED m_led;
    private int m_rainbowFirstPixelHue;

    public ColorLED(int port, int length) {
        m_led = new AddressableLED(port);
        m_ledBuffer = new AddressableLEDBuffer(length);
        //144 lights on big strand
        //75 seems to be max amount before something explodes
        //8 lights on small strand
        m_led.setLength(m_ledBuffer.getLength());
    
        m_led.setData(m_ledBuffer);
    
        m_led.start();
    }
    
    public void setLED(int red, int green, int blue) {
        // TODO test whether inputs greater than 255 or less than 0 need to be clamped

        for (var i = 0; i < m_ledBuffer.getLength(); i++) {
            // Set the value
            m_ledBuffer.setRGB(i, red, green, blue);
        }

        m_led.setData(m_ledBuffer);

    }
}
