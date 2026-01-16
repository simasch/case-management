# Requirements Catalog

## Functional Requirements

| ID     | Title                      | User Story                                                                                                              | Priority | Status |
|--------|----------------------------|-------------------------------------------------------------------------------------------------------------------------|----------|--------|
| FR-001 | Create Case                | As a service agent, I want to create new service cases with relevant details so that I can track customer issues.       | High     | Open   |
| FR-002 | Associate Customer to Case | As a service agent, I want to associate cases with customers so that I can track all issues for a specific customer.    | High     | Open   |
| FR-003 | Track Case Status          | As a service manager, I want to monitor case status and progress so that I can ensure timely resolution.                | High     | Open   |
| FR-004 | Set Case Priority          | As a service agent, I want to set case priority levels so that critical issues are handled first.                       | High     | Open   |
| FR-005 | Update Case                | As a service agent, I want to update case details so that I can keep information current.                               | High     | Open   |
| FR-006 | Assign Case                | As a service manager, I want to assign cases to team members so that work is distributed appropriately.                 | High     | Open   |
| FR-007 | Reassign Case              | As a service manager, I want to reassign cases to different team members so that I can balance workload.                | High     | Open   |
| FR-008 | Close Case                 | As a service agent, I want to close resolved cases so that I can maintain an accurate case inventory.                   | High     | Open   |
| FR-009 | View Case Audit Trail      | As a service manager, I want to view a full audit trail of case changes so that I can track accountability.             | High     | Open   |
| FR-010 | Tag Cases                  | As a service agent, I want to tag cases with keywords so that I can categorize and retrieve them efficiently.           | Medium   | Open   |
| FR-011 | Categorize Cases           | As a service agent, I want to categorize cases by type so that I can organize them logically.                           | Medium   | Open   |
| FR-012 | Add Case Notes             | As a service agent, I want to add notes to cases so that I can document important information.                          | High     | Open   |
| FR-013 | Add Case Comments          | As a service agent, I want to add comments to cases so that I can communicate with team members.                        | High     | Open   |
| FR-014 | Upload Case Attachments    | As a service agent, I want to upload documents to cases so that I can store relevant files.                             | High     | Open   |
| FR-015 | View Case Attachments      | As a service agent, I want to view and download case attachments so that I can access stored documents.                 | High     | Open   |
| FR-016 | View Case History          | As a service agent, I want to view comprehensive case history so that I understand the full context.                    | High     | Open   |
| FR-017 | Search Cases by Status     | As a service agent, I want to search cases by status so that I can find specific cases quickly.                         | High     | Open   |
| FR-018 | Search Cases by Priority   | As a service agent, I want to search cases by priority so that I can focus on urgent issues.                            | High     | Open   |
| FR-019 | Search Cases by Customer   | As a service agent, I want to search cases by customer so that I can view all issues for a specific customer.           | High     | Open   |
| FR-020 | Search Cases by Assignee   | As a service manager, I want to search cases by assignee so that I can monitor team member workload.                    | High     | Open   |
| FR-021 | Search Cases by Tags       | As a service agent, I want to search cases by tags so that I can find related cases.                                    | Medium   | Open   |
| FR-022 | Filter Cases by Date Range | As a service manager, I want to filter cases by date range so that I can analyze trends over time.                      | Medium   | Open   |
| FR-023 | Create Customer Record     | As a service agent, I want to create customer records for individuals so that I can maintain customer information.      | High     | Open   |
| FR-024 | Create Organization Record | As a service agent, I want to create customer records for organizations so that I can manage business customers.        | High     | Open   |
| FR-025 | Update Customer Record     | As a service agent, I want to update customer information so that I can keep records current.                           | High     | Open   |
| FR-026 | Link Cases to Customer     | As a service agent, I want to link multiple cases to a customer so that I can track relationship history.               | High     | Open   |
| FR-027 | View Customer Case History | As a service agent, I want to view all cases associated with a customer so that I understand the customer relationship. | High     | Open   |
| FR-028 | Manage Case Workflow       | As a service manager, I want to define workflow stages for cases so that cases follow a consistent process.             | Medium   | Open   |
| FR-029 | Track Case Progress        | As a service agent, I want to track case progress through workflow stages so that I know what steps remain.             | Medium   | Open   |

## Non-Functional Requirements

| ID      | Title                 | Requirement                                                                                     | Category        | Priority | Status |
|---------|-----------------------|-------------------------------------------------------------------------------------------------|-----------------|----------|--------|
| NFR-001 | Page Load Performance | All page loads must complete within 2 seconds under normal load conditions.                     | Performance     | High     | Open   |
| NFR-002 | Search Response Time  | Case search results must be returned within 1 second for up to 10,000 cases.                    | Performance     | High     | Open   |
| NFR-003 | Concurrent Users      | System must support at least 50 concurrent users without performance degradation.               | Scalability     | High     | Open   |
| NFR-004 | Data Encryption       | All data in transit must use TLS 1.3 encryption.                                                | Security        | High     | Open   |
| NFR-005 | Password Security     | User passwords must be hashed using BCrypt with minimum cost factor of 10.                      | Security        | High     | Open   |
| NFR-006 | Role-Based Access     | System must implement role-based access control for all operations.                             | Security        | High     | Open   |
| NFR-007 | Session Management    | User sessions must use JWT tokens with configurable expiration.                                 | Security        | High     | Open   |
| NFR-008 | Audit Logging         | All case modifications must be logged with user, timestamp, and change details.                 | Security        | High     | Open   |
| NFR-009 | Data Backup           | System data must be backed up daily with point-in-time recovery capability.                     | Availability    | High     | Open   |
| NFR-010 | Browser Compatibility | UI must work correctly in Chrome, Firefox, and Safari (latest 2 versions).                      | Usability       | High     | Open   |
| NFR-011 | Responsive Design     | UI must be responsive and usable on desktop, tablet, and mobile devices.                        | Usability       | Medium   | Open   |
| NFR-012 | Internationalization  | UI must support multiple languages with externalized message bundles.                           | Usability       | Medium   | Open   |
| NFR-013 | Attachment Size Limit | Individual file attachments must be limited to 10 MB maximum size.                              | Performance     | Medium   | Open   |
| NFR-014 | Code Quality          | Code must pass Error Prone and NullAway static analysis without warnings.                       | Maintainability | High     | Open   |
| NFR-015 | Test Coverage         | Code coverage must be at least 80% for unit tests and 70% overall.                              | Maintainability | High     | Open   |
| NFR-016 | Database Transactions | All data modifications must use database transactions with optimistic locking.                  | Availability    | High     | Open   |
| NFR-017 | Error Handling        | System must provide user-friendly error messages and log technical details for troubleshooting. | Usability       | High     | Open   |

## Constraints

| ID    | Title                 | Constraint                                                                          | Category    | Priority | Status |
|-------|-----------------------|-------------------------------------------------------------------------------------|-------------|----------|--------|
| C-001 | Java Runtime Version  | Backend must run on Java 25 or higher.                                              | Technical   | High     | Open   |
| C-002 | Spring Boot Version   | System must use Spring Boot 4.0.1 or compatible version.                            | Technical   | High     | Open   |
| C-003 | Database Platform     | System must use PostgreSQL as the database.                                         | Technical   | High     | Open   |
| C-004 | UI Framework          | Frontend must be built with Vaadin Flow (Java-based, not React).                    | Technical   | High     | Open   |
| C-005 | Database Access       | Database access must use jOOQ for type-safe SQL queries.                            | Technical   | High     | Open   |
| C-006 | Database Migrations   | Schema changes must be managed through Flyway migrations.                           | Technical   | High     | Open   |
| C-007 | Container Runtime     | Development and testing must support Docker or Testcontainers Cloud.                | Technical   | High     | Open   |
| C-008 | Code Formatting       | Code must follow Spring Java Format style.                                          | Technical   | High     | Open   |
| C-009 | Architecture Patterns | Code must follow layered architecture (UI, Security, Domain) enforced by ArchUnit.  | Technical   | High     | Open   |
| C-010 | Testing Strategy      | System must use Karibu Testing for unit tests and Playwright for integration tests. | Technical   | High     | Open   |
| C-011 | Production Deployment | System must be deployable as a standalone JAR file.                                 | Operational | High     | Open   |
| C-012 | Build Tool            | Project must use Maven as the build tool.                                           | Technical   | High     | Open   |
