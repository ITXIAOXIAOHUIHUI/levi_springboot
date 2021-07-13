package com.springboot.levi.leviweb1.cache.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.springboot.levi.leviweb1.dto.ReqBody;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;


@Slf4j
@Service
public class RoadWayService {

    private LoadingCache<WarehouseZone, Map<String, ReqBody>> roadawyCache = null;
    private static final long CACHE_MAX_SIZE = 1000L;
    private static final long EXPIRE_SECONDS = 10L;

    public synchronized void initRoadWayCache(Long warehouseId,String zoneCode) throws ExecutionException {
        if (null != roadawyCache) {
            log.info("is not null  ");
            Map<String, ReqBody> stringReqBodyMap = roadawyCache.get(WarehouseZone.builder().warehouseId(warehouseId).zoneCode(zoneCode).build());
            log.info(stringReqBodyMap.toString());
            return;
        }
        roadawyCache = CacheBuilder.newBuilder()
                .maximumSize(CACHE_MAX_SIZE)
                .expireAfterAccess(EXPIRE_SECONDS, TimeUnit.MINUTES)
                .expireAfterWrite(EXPIRE_SECONDS, TimeUnit.MINUTES).build(
                        new CacheLoader<WarehouseZone, Map<String, ReqBody>>() {
                            @Override
                            public Map<String, ReqBody> load(@Nonnull WarehouseZone warehouseZone) {
                                log.info("111111111111");
                                Map<String, ReqBody> roadWayMap = new HashMap<>();
                                for (int i = 0; i <= 20; i++) {
                                    ReqBody reqBody = new ReqBody();
                                    reqBody.setSourceTaskId("s001" + i);
                                    reqBody.setSourceTaskId("t001" + i);
                                    roadWayMap.put("i" + i, reqBody);
                                }
                                return roadWayMap;
                            }

                        });
        Map<String, ReqBody> stringReqBodyMap = roadawyCache.get(WarehouseZone.builder().warehouseId(warehouseId).zoneCode(zoneCode).build());
        log.info(stringReqBodyMap.toString());
    }


    @Builder
    private static class WarehouseZone {
        Long warehouseId;
        String zoneCode;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            WarehouseZone that = (WarehouseZone) o;
            return Objects.equals(warehouseId, that.warehouseId) &&
                    Objects.equals(zoneCode, that.zoneCode);
        }

        @Override
        public int hashCode() {
            return Objects.hash(warehouseId, zoneCode);
        }
    }
}
