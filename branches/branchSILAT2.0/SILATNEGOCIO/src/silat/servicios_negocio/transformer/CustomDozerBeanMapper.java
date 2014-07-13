package silat.servicios_negocio.transformer;

import java.lang.reflect.Proxy;

import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import net.sf.dozer.util.mapping.CustomFieldMapperIF;
import net.sf.dozer.util.mapping.DozerInitializer;
import net.sf.dozer.util.mapping.MapperIF;
import net.sf.dozer.util.mapping.MappingException;
import net.sf.dozer.util.mapping.cache.CacheManager;
import net.sf.dozer.util.mapping.cache.DozerCacheManager;
import net.sf.dozer.util.mapping.classmap.Configuration;
import net.sf.dozer.util.mapping.config.GlobalSettings;
import net.sf.dozer.util.mapping.interceptor.StatisticsInterceptor;
import net.sf.dozer.util.mapping.stats.GlobalStatistics;
import net.sf.dozer.util.mapping.stats.StatisticTypeConstants;
import net.sf.dozer.util.mapping.stats.StatisticsManager;
import net.sf.dozer.util.mapping.util.CustomMappingsLoader;
import net.sf.dozer.util.mapping.util.InitLogger;
import net.sf.dozer.util.mapping.util.LoadMappingsResult;
import net.sf.dozer.util.mapping.util.MapperConstants;
import net.sf.dozer.util.mapping.util.MappingUtils;

public class CustomDozerBeanMapper implements MapperIF {
    private static final Log log = LogFactory.getLog(CustomDozerBeanMapper.class);
    private static final StatisticsManager statsMgr = GlobalStatistics.getInstance().getStatsMgr();

    static {
        //TODO deberia spring administrar el registro de los mbeans
        //DozerInitializer.init();
    }
    /*
         * Accessible for custom injection
         */
    private List mappingFiles; // String file names
    private List customConverters;
    private List eventListeners;
    private CustomFieldMapperIF customFieldMapper;
    private Map customConvertersWithId;

    /*
         * Not accessible for injection
         */
    private Map customMappings;
    private Configuration globalConfiguration;
    // There are no global caches. Caches are per bean mapper instance
    private CacheManager cacheManager;

    /**
     *
     */
    public CustomDozerBeanMapper() {
        this(null, null);
    }

    /**
     * @param mappingFiles
     * @param eventListeners
     * @param cacheManager
     */
    public CustomDozerBeanMapper(List mappingFiles, List eventListeners, CacheManager cacheManager) {
        this.cacheManager = cacheManager;
        setMappingFiles(mappingFiles);
        init();
        setEventListeners(eventListeners);
        getMappingProcessor();
    }

    /**
     * @param mappingFiles
     * @param eventListeners
     */
    public CustomDozerBeanMapper(List mappingFiles, List eventListeners) {
        cacheManager = new DozerCacheManager();
        setMappingFiles(mappingFiles);
        init();
        setEventListeners(eventListeners);
        getMappingProcessor();
    }

    public void map(Object source, Object destination, String mapId) throws MappingException {
        getMappingProcessor().map(source, destination, mapId);
    }

    public Object map(Object source, Class destinationClass, String mapId) throws MappingException {
        return getMappingProcessor().map(source, destinationClass, mapId);
    }

    public Object map(Object source, Class destinationClass) throws MappingException {
        return getMappingProcessor().map(source, destinationClass);
    }

    public void map(Object source, Object destination) throws MappingException {
        getMappingProcessor().map(source, destination);
    }

    public List getMappingFiles() {
        return mappingFiles;
    }

    public void setMappingFiles(List mappingFiles) {
        this.mappingFiles = mappingFiles;
    }

    public void setFactories(Map factories) {
        MappingUtils.storedFactories.putAll(factories);
    }

    public void setCustomConverters(List customConverters) {
        this.customConverters = customConverters;
    }

    private void init() {
        InitLogger.log(log, "Initializing a new instance of the dozer bean mapper.");

        // initialize any bean mapper caches. These caches are only visible to the bean mapper instance and
        // are not shared across the VM.
        cacheManager.addCache(MapperConstants.CONVERTER_BY_DEST_TYPE_CACHE,
                              GlobalSettings.getInstance().getConverterByDestTypeCacheMaxSize());
        cacheManager.addCache(MapperConstants.SUPER_TYPE_CHECK_CACHE,
                              GlobalSettings.getInstance().getSuperTypesCacheMaxSize());

        // stats
        statsMgr.increment(StatisticTypeConstants.MAPPER_INSTANCES_COUNT);
    }

    protected MapperIF getMappingProcessor() {
        if (customMappings == null) {
            loadCustomMappings();
        }
        MapperIF processor =
            new CustomMappingProcessor(customMappings, globalConfiguration, cacheManager, statsMgr, customConverters,
                                       getEventListeners(), getCustomFieldMapper(), customConvertersWithId);

        // If statistics are enabled, then Proxy the processor with a statistics interceptor
        if (statsMgr.isStatisticsEnabled()) {
            processor =
                    (MapperIF)Proxy.newProxyInstance(processor.getClass().getClassLoader(), processor.getClass().getInterfaces(),
                                                     new StatisticsInterceptor(processor, statsMgr));
        }

        return processor;
    }

    private synchronized void loadCustomMappings() {
        if (this.customMappings == null) {
            CustomMappingsLoader customMappingsLoader = new CustomMappingsLoader();
            LoadMappingsResult loadMappingsResult = customMappingsLoader.load(mappingFiles);
            this.customMappings = loadMappingsResult.getCustomMappings();
            this.globalConfiguration = loadMappingsResult.getGlobalConfiguration();
        }
    }

    public List getEventListeners() {
        return eventListeners;
    }

    public void setEventListeners(List eventListeners) {
        this.eventListeners = eventListeners;
    }

    public CustomFieldMapperIF getCustomFieldMapper() {
        return customFieldMapper;
    }

    public void setCustomFieldMapper(CustomFieldMapperIF customFieldMapper) {
        this.customFieldMapper = customFieldMapper;
    }

    public Map getCustomConvertersWithId() {
        return customConvertersWithId;
    }

    public void setCustomConvertersWithId(Map customConvertersWithId) {
        this.customConvertersWithId = customConvertersWithId;
    }

    /**
     * Fastidia integracion..
     * @param format
     */
    public void setDateFormat(String format) {
        this.globalConfiguration.setDateFormat(format);
    }

    /**
         * @param cacheManager the cacheManager to set
         */
    /*public void setCacheManager(CacheManagerIF cacheManager) {
            this.cacheManager = cacheManager;
        }*/

}
