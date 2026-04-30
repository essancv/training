# Functional Domain Map
## Domains
### DOMAIN001 PR Analysis
Handles diff ingestion prompt creation AI invocation and result interpretation
### DOMAIN002 Integration
GitHub and CI CD event handling webhook processing
### DOMAIN003 Prompt Engine
Template management versioning and dynamic prompt assembly
### DOMAIN004 Reporting
PR comments structured reports and API responses
### DOMAIN005 Configuration
Repository level settings cost limits feature flags
### DOMAIN006 Security
Auth encryption OWASP detection and secure processing
### DOMAIN007 Observability
Logging metrics tracing and audit trails
## Domain map
```mermaid
flowchart TD
D1['PR Analysis'] --> D2['Integration']
D2 --> D3['prompt Engine']
D3 --> D4['Reporting']
D4 --> D5['Configuration']
D5 --> D6['Security']
D6 --> D7['Observability']
```