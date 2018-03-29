package net.sf.javaanpr.test;
import static org.junit.Assert.*;

import net.sf.javaanpr.imageanalysis.CarSnapshot;
import net.sf.javaanpr.intelligence.Intelligence;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;


import java.io.*;
import java.util.Properties;

@RunWith(Parameterized.class)
public class RecognitionAllIT {
    private File snapshot;

    // With this parameterized test we can make sure that all test runs through - even if there are fails (which there are)
    @Parameterized.Parameters(name = "Testing image: {0}")
        public static File[] parameters(){
        String snapshotDirPath = "src/test/resources/snapshots";

        File snapshotDir = new File(snapshotDirPath);
        File[] snapshots = snapshotDir.listFiles();

        // I use Hamcrest matchers which make more sense than using assertEquals
        assertThat(snapshots, not(nullValue()));
        assertThat(snapshots.length, greaterThan(0));

        return snapshots;
        }


    public RecognitionAllIT(File snapshot) {
        this.snapshot = snapshot;
    }

    // Instead of using loggers (which can be difficult to analyze) I use asserts. 
    @Test
    public void testAllSnapshots() throws IOException, ParserConfigurationException, SAXException {
        String resultsPath = "src/test/resources/results.properties";
        InputStream resultsStream = new FileInputStream(new File(resultsPath));

        Properties properties = new Properties();
        properties.load(resultsStream);
        resultsStream.close();
        assertThat(properties.size(), greaterThan(0));

        Intelligence intelligence = new Intelligence();
        assertThat(intelligence, not(nullValue()));

        CarSnapshot carSnapshot = new CarSnapshot(new FileInputStream(snapshot));
        assertThat("Carsnapshot is not null", carSnapshot, not(nullValue()));
        assertThat("Carsnapshot.image is not null", carSnapshot.getImage(), not(nullValue()));

        String snapName = snapshot.getName();
        String plateCorrect = properties.getProperty(snapName);
        assertThat(plateCorrect, not(nullValue()));

        String numberPlate = intelligence.recognize(carSnapshot, false);
        assertThat(numberPlate, is(plateCorrect));

        carSnapshot.close();
    }
}
