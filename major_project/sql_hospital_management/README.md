# Hospital Management System SQL View

A robust RDBMS framework establishing associations between hospital care providers, subjects, diagnostics, and schedules.

## Structure
- `Patients`: Registry of individuals receiving care.
- `Doctors`: Register of specialists and their varied billing models.
- `Treatments`: Cost parameters connected explicitly to respective disease vectors.
- `Appointments`: Central linking nexus controlling datetime execution logs mapping Patients -> Doctors -> Treatments.

## Analytics Metrics Designed
1. **Most Consulted Doctors**: Assesses workload capacities across specific doctors.
2. **Monthly Revenue Engine**: Sums combination totals matching Treatment Codes + Specialist Consultation fees.
3. **Common Diseases**: Evaluates the most frequent illness profiles assigned through successful appointments.
4. **Patient Visit Frequency**: Determines the volume of repeat engagements per individual.
5. **Doctor Performance**: Isolates the independent fee aggregation derived solely from Doctors (exclusive of treatments).

## Usage
Simply load the `.sql` script into your database client to initialize constraints, inject testing data, and query insights.
