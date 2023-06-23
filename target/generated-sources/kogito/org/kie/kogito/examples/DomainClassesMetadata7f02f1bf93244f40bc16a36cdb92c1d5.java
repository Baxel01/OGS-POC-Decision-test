package org.kie.kogito.examples;
public class DomainClassesMetadata7f02f1bf93244f40bc16a36cdb92c1d5 {

    public static final org.drools.model.DomainClassMetadata org_kie_kogito_examples_demo_Person_Metadata_INSTANCE = new org_kie_kogito_examples_demo_Person_Metadata();
    private static class org_kie_kogito_examples_demo_Person_Metadata implements org.drools.model.DomainClassMetadata {

        @Override
        public Class<?> getDomainClass() {
            return org.kie.kogito.examples.demo.Person.class;
        }

        @Override
        public int getPropertiesSize() {
            return 5;
        }

        @Override
        public int getPropertyIndex( String name ) {
            switch(name) {
                case "this": return 0;
                case "adult": return 1;
                case "age": return 2;
                case "name": return 3;
                case "retired": return 4;
             }
             throw new RuntimeException("Unknown property '" + name + "' for class class class org.kie.kogito.examples.demo.Person");
        }
    }
    public static final org.drools.model.DomainClassMetadata org_drools_ruleunits_api_DataStore_Metadata_INSTANCE = new org_drools_ruleunits_api_DataStore_Metadata();
    private static class org_drools_ruleunits_api_DataStore_Metadata implements org.drools.model.DomainClassMetadata {

        @Override
        public Class<?> getDomainClass() {
            return org.drools.ruleunits.api.DataStore.class;
        }

        @Override
        public int getPropertiesSize() {
            return 1;
        }

        @Override
        public int getPropertyIndex( String name ) {
            switch(name) {
                case "this": return 0;
             }
             throw new RuntimeException("Unknown property '" + name + "' for class class interface org.drools.ruleunits.api.DataStore");
        }
    }
}