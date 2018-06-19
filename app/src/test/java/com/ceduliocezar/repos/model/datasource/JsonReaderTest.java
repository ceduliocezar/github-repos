package com.ceduliocezar.repos.model.datasource;

import com.ceduliocezar.repos.TestUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.net.URL;

import static junit.framework.Assert.assertNotNull;

/**
 * Test suite for {@link JsonReader}
 */
@RunWith(MockitoJUnitRunner.class)
public class JsonReaderTest {

    @InjectMocks
    private JsonReader jsonReader;

    @Test
    public void test_read() throws Exception {
        URL resource = TestUtils.urlFromResources(getClass(), "test-200-repo-response.json");

        String json = jsonReader.read(resource);

        assertNotNull(json);
    }

    @Test(expected = Exception.class)
    public void test_readFail() throws Exception {
        jsonReader.read(null);
    }
}