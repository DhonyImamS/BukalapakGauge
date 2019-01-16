package BizzyPhoenixGauge.Element;

public class ElLogin {

    protected String IdElement;
    protected String XPathElement;
    protected String ClassElement;
    protected String NameElement;
    protected String TextElement;
    protected String CSSElement;
    protected String CategoryElement;



    public ElLogin _fieldUsername() {
        this.NameElement = "email";
        this.CSSElement = ".ts-textfield-officeemail";
        return this;
    }

    public ElLogin _fieldPassword() {
        this.IdElement = "pass";
        this.NameElement = "password";
        this.CSSElement = ".ts-textfield-password";
        return this;
    }

    public ElLogin _buttonLogin() {
        this.CSSElement = ".ts-button-login";
        return this;
    }



    /** --------------------------------------------- GETTER SETTER HERE --------------------------------------------- **/
    public String getCSSElement() {

        return CSSElement;
    }

    public void setCSSElement(String csselement) {

        this.CSSElement = csselement;
    }

    public String getIdElement() {

        return IdElement;
    }

    public void setIdElement(String idElement) {

        this.IdElement = idElement;
    }

    public String getXPathElement() {
        return XPathElement;
    }

    public void setXPathElement(String XPathElement) {

        this.XPathElement = XPathElement;
    }

    public String getClassElement() {

        return ClassElement;
    }

    public void setClassElement(String classElement) {

        this.ClassElement = classElement;
    }

    public String getNameElement() {

        return NameElement;
    }

    public void setNameElement(String nameElement) {

        this.NameElement = nameElement;
    }

    public String getTextElement() {

        return TextElement;
    }

    public void setTextElement(String textElement) {

        this.TextElement = textElement;
    }

    public String getCategoryElement() {

        return CategoryElement;
    }

    public void setCategoryElement(String categoryElement) {

        this.CategoryElement = categoryElement;
    }
}
