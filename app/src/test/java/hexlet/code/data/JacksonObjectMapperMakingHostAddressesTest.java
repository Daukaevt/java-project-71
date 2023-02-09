package hexlet.code.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JacksonObjectMapperMakingHostAddressesTest {

    @Test
    void parse() {
        JacksonObjectMapperMakingHostAddresses jacksonObjectMapperMakingHostAddresses =
                new JacksonObjectMapperMakingHostAddresses();
        var testStr = jacksonObjectMapperMakingHostAddresses.parse(
                "{\"testKey\": \"testValue\"}"
        );
        assertEquals("testValue", testStr.get("testKey"));
    }
}