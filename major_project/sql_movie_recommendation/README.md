# SQL Movie Recommendation Engine

An exhaustive SQL schema managing the complex associations required for collaborative filtering systems utilized by large streaming platforms.

## Configuration
- `Users`: Defines consumer identities.
- `Movies`: Maintains title details mapped strongly by strings (Genre indexing).
- `Watch_History`: Records implicit engagements (simply clicking on or watching a film without inherently giving a rating).
- `Ratings`: Records explicit engagements mapped to constraints (`CHECK` ensuring values between 1 and 5 only).

## ML-Style Operations (Queries)
1. **Top-Rated Films**: Summarizes average rating weights.
2. **Popularity Engagements**: Dissects volume by broader categories (Genres).
3. **Trending**: Highlights the specific titles seeing the most historical views.
4. **Volume Behaviors**: Measures "binge" style volumes grouping by the consumer.
5. **Cold-Start Recommendations**: Employs sub-query structures to find the best globally rated `MovieID` strictly filtering out IDs present in an exact user's `Watch_History` log, effectively "recommending" them something new but highly reputed.
