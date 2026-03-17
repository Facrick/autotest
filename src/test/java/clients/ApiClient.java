package clients;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;

import java.util.Map;

public class ApiClient {
    private static ApiClient instance;
    private Playwright playwright;
    private APIRequestContext requestContext;

    private ApiClient() {
        playwright = Playwright.create();
        requestContext = playwright.request().newContext(new APIRequest.NewContextOptions()
                .setBaseURL("https://jsonplaceholder.typicode.com")
                .setExtraHTTPHeaders(Map.of(
                        "Accept", "application/json",
                        "Content-Type", "application/json"
                )));
    }

    public static synchronized ApiClient getInstance() {
        if (instance == null) {
            instance = new ApiClient();
        }
        return instance;
    }

    public APIResponse get(String endpoint) {
        return requestContext.get(endpoint);
    }

    public APIResponse get(String endpoint, RequestOptions options) {
        return requestContext.get(endpoint, options);
    }

    public APIResponse post(String endpoint, Object data) {
        return requestContext.post(endpoint, RequestOptions.create().setData(data));
    }

    public APIResponse put(String endpoint, Object data) {
        return requestContext.put(endpoint, RequestOptions.create().setData(data));
    }

    public APIResponse delete(String endpoint) {
        return requestContext.delete(endpoint);
    }

    public void close() {
        if (requestContext != null) {
            requestContext.dispose();
        }
        if (playwright != null) {
            playwright.close();
        }
        instance = null;
    }
}