package solutions.ntq.social.ntq_fresher_social.utils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.hibernate.proxy.HibernateProxy;

import java.io.IOException;

public class HibernateProxyTypeAdapter extends TypeAdapter<HibernateProxy> {
    @Override
    public void write(JsonWriter out, HibernateProxy value) throws IOException {
    }

    @Override
    public HibernateProxy read(JsonReader in) throws IOException {
        return null;
    }
}