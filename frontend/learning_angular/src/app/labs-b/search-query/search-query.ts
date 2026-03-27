import { Component } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-search-query',
  imports: [RouterLink],
  templateUrl: './search-query.html',
})
export class SearchQueryComponent {
  protected query = '';
  protected status = 'all';

  constructor(
    private readonly route: ActivatedRoute,
    private readonly router: Router,
  ) {
    this.route.queryParamMap.subscribe((params) => {
      this.query = params.get('q') ?? '';
      this.status = params.get('status') ?? 'all';
    });
  }

  protected onQueryInput(event: Event): void {
    const target = event.target as HTMLInputElement;
    this.query = target.value;
  }

  protected onStatusChange(event: Event): void {
    const target = event.target as HTMLSelectElement;
    this.status = target.value;
  }

  protected applyFilters(): void {
    this.router.navigate([], {
      relativeTo: this.route,
      queryParams: {
        q: this.query || null,
        status: this.status === 'all' ? null : this.status,
      },
      queryParamsHandling: 'merge',
    });
  }
}
