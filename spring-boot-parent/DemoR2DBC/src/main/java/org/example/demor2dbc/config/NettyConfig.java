package org.example.demor2dbc.config;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.boot.web.embedded.netty.NettyServerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.netty.http.server.HttpServer;

@Configuration
public class NettyConfig {

    @Bean
    public NettyReactiveWebServerFactory nettyReactiveWebServerFactory() {
        NettyReactiveWebServerFactory factory = new NettyReactiveWebServerFactory();
        factory.addServerCustomizers(new EventLoopNettyCustomizer());
        return factory;
    }

    private static class EventLoopNettyCustomizer implements NettyServerCustomizer {
        @Override
        public HttpServer apply(HttpServer httpServer) {
            // Создаем EventLoopGroup с нужным количеством потоков
            EventLoopGroup eventLoopGroup = new NioEventLoopGroup(8); // 8 worker потоков
            return httpServer.runOn(eventLoopGroup);
        }
    }
}
