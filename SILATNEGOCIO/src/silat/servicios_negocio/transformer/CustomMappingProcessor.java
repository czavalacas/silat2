package silat.servicios_negocio.transformer;

import java.util.List;
import java.util.Map;

import net.sf.dozer.util.mapping.CustomFieldMapperIF;
import net.sf.dozer.util.mapping.MappingProcessor;
import net.sf.dozer.util.mapping.cache.CacheManager;
import net.sf.dozer.util.mapping.classmap.Configuration;
import net.sf.dozer.util.mapping.stats.StatisticsManager;

/**
 * @author MPLB mplescano@gmail.com
 *
 */
public class CustomMappingProcessor extends MappingProcessor {

    /**
     * @param customMappings
     * @param globalConfiguration
     * @param cacheMgr
     * @param statsMgr
     * @param customConverterObjects
     * @param eventListeners
     * @param customFieldMapper
     * @param customConverterObjectsWithId
     */
    protected CustomMappingProcessor(Map customMappings, Configuration globalConfiguration, CacheManager cacheMgr,
                                     StatisticsManager statsMgr, List customConverterObjects, List eventListeners,
                                     CustomFieldMapperIF customFieldMapper, Map customConverterObjectsWithId) {
        super(customMappings, globalConfiguration, cacheMgr, statsMgr, customConverterObjects, eventListeners,
              customFieldMapper, customConverterObjectsWithId);
    }
}
