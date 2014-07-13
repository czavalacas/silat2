package siat.tagclasses;

import java.util.logging.Level;
import java.util.Map;
import javax.faces.component.StateHolder;
import javax.faces.component.UIComponent;
import javax.faces.component.behavior.ClientBehaviorBase;
import javax.faces.component.behavior.ClientBehaviorContext;
import javax.faces.component.behavior.FacesBehavior;
import javax.faces.context.FacesContext;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.input.RichInputComboboxListOfValues;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputListOfValues;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;

@FacesBehavior("lubal.silat.adfsilat.watermark")
public class SetWatermarkBehavior extends ClientBehaviorBase implements StateHolder {
    private static final String UNSUPPORTED_ELEMENT = "unsupported";
    private static final String TEXTAREA_ELEMENT = "textarea";
    private static final String INPUT_ELEMENT = "input";
    private static final String EMPTY_DEFAULT = "Enter Value";
    private static final String SCRIPT_ARG_SEPARATOR = "\",\"";
    private static final String SCRIPT_PREFIX = "addWatermarkBehavior(\"";
    private static final String SCRIPT_SUFFIX = "\");";
    String _value;
    private static ADFLogger _logger = ADFLogger.createADFLogger(SetWatermarkBehavior.class);

    public SetWatermarkBehavior() {
        super();
    }

    @Override
    public String getScript(ClientBehaviorContext ctx) {

        UIComponent component = ctx.getComponent();

        //only continue if the component is currently rendered
        if (component.isRendered()) {
            String componentType = deduceDOMElementType(component);

            //Only continue if the component is a valid type
            if (!componentType.equals(UNSUPPORTED_ELEMENT)) {
                String wmText = (getValue() == null) ? EMPTY_DEFAULT : getValue();
                StringBuilder script = new StringBuilder(SCRIPT_PREFIX);
                script.append(ctx.getSourceId());
                script.append(SCRIPT_ARG_SEPARATOR);
                script.append(componentType);
                script.append(SCRIPT_ARG_SEPARATOR);
                script.append(wmText);
                script.append(SCRIPT_SUFFIX);

                if (_logger.isLoggable(Level.INFO)) {
                    StringBuilder logMsg = new StringBuilder("Information");
                    logMsg.append("Watermark set script: ");
                    logMsg.append(script.toString());
                    _logger.info(logMsg.toString());
                }


                //We don't have an init event, just valueChange so push the code this way
                FacesContext fctx = ctx.getFacesContext();
                ExtendedRenderKitService extendedRenderKitService =
                    Service.getRenderKitService(fctx, ExtendedRenderKitService.class);
                extendedRenderKitService.addScript(fctx, script.toString());
            }
        }


        return "";
    }

    /**
     * We'll only attach this to the various input types so this method does a quick sanity check there
     * and then works out the underlying DOM element type that the placeholder attribute will eventually
     * have to be applied to
     * @param component
     * @return DOM element type
     */
    private String deduceDOMElementType(UIComponent component) {
        String componentType = UNSUPPORTED_ELEMENT;
        //work out the correct component type
        if (component instanceof RichInputText) {
            //In this case we may have a multi-line item but assume intially that this is not the case
            componentType = INPUT_ELEMENT;

            //Now check for the rows attribute to see if this is multi-line
            Map<String, Object> compAttr = component.getAttributes();
            if (compAttr.containsKey("rows")) {
                int rows = (Integer)compAttr.get("rows");
                if (rows > 1) {
                    componentType = TEXTAREA_ELEMENT;
                }
            }

        } else if (component instanceof RichInputDate || component instanceof RichInputListOfValues ||
                   component instanceof RichInputComboboxListOfValues) {
            //These all resolve to inputs at the DOM level
            componentType = INPUT_ELEMENT;

        }
        return componentType;
    }


    public void setValue(String value) {
        this._value = value;
    }

    public String getValue() {
        return _value;
    }

    @Override
    public Object saveState(FacesContext facesContext) {
        Object toSave[] = new Object[2];
        toSave[0] = super.saveState(facesContext);
        toSave[1] = _value;
        return (toSave);
    }

    @Override
    public void restoreState(FacesContext facesContext, Object state) {
        Object values[] = (Object[])state;
        super.restoreState(facesContext, values[0]);
        _value = (String)values[1];
    }

    @Override
    public boolean isTransient() {
        return false;
    }

    @Override
    public void setTransient(boolean b) {
    }
}