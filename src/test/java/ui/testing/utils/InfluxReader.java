package ui.testing.utils;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.query.FluxTable;

import java.util.List;

public class InfluxReader {

    public static void printAllureMetrics() {
        String url = System.getenv("INFLUX_URL");
        String token = System.getenv("INFLUX_TOKEN");
        String org = System.getenv("INFLUX_ORG");
        String bucket = System.getenv("INFLUX_BUCKET");
        // 👇 Добавь проверки
        if (url == null || token == null || org == null || bucket == null) {
            System.err.println("⚠️  InfluxDB environment variables are not set!");
            System.err.println("Set: INFLUX_URL, INFLUX_TOKEN, INFLUX_ORG, INFLUX_BUCKET");
            return;
        }

        try (InfluxDBClient client = InfluxDBClientFactory.create(url, token.toCharArray(), org)) {

            String flux = String.format("""
                from(bucket: "%s")
                  |> range(start: -24h)
                  |> filter(fn: (r) => r["_measurement"] == "allure_test")
                  |> group(columns: ["status"])
                  |> count()
                """, bucket);

            List<FluxTable> tables = client.getQueryApi().query(flux, org);

            System.out.println("🧪 Test results from InfluxDB (last 24h):");
            for (FluxTable table : tables) {
                table.getRecords().forEach(rec -> {
                    System.out.printf("%s : %s%n",
                            rec.getValueByKey("status"),
                            rec.getValue());
                });
            }

        } catch (Exception e) {
            System.err.println("⚠️  Failed to query InfluxDB: " + e.getMessage());
        }
    }
}
