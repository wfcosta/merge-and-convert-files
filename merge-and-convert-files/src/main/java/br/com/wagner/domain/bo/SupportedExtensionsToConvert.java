package br.com.wagner.domain.bo;

public enum SupportedExtensionsToConvert {

    TXT("PDF");

    public final String label;

    SupportedExtensionsToConvert(String label) {
        this.label = label;
    }
}
